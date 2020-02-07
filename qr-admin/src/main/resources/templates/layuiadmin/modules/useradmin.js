/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */


layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //用户管理
    table.render({
        elem: '#LAY-user-manage'
        , url: layui.setter.base + 'json/useradmin/webuser.js' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 100, title: 'ID', sort: true}
            , {field: 'username', title: '用户名', minWidth: 100}
            , {field: 'avatar', title: '头像', width: 100, templet: '#imgTpl'}
            , {field: 'phone', title: '手机'}
            , {field: 'email', title: '邮箱'}
            , {field: 'sex', width: 80, title: '性别'}
            , {field: 'ip', title: 'IP'}
            , {field: 'jointime', title: '加入时间', sort: true}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-webuser'}
        ]]
        , page: true
        , limit: 30
        , height: 'full-220'
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-user-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.prompt({
                formType: 1
                , title: '敏感操作，请验证口令'
            }, function (value, index) {
                layer.close(index);

                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            });
        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑用户'
                , content: '../../../views/user/user/userform.html'
                , maxmin: true
                , area: ['500px', '450px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'LAY-user-front-submit'
                        , submit = layero.find('iframe').contents().find('#' + submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        table.reload('LAY-user-front-submit'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            });
        }
    });

    //管理员管理
    table.render({
        elem: '#LAY-user-back-manage'
        , url: '/basicSystem/getAllAdmin'
        , method: 'POST'
        , cols: [[
            {type: 'checkbox', fixed: 'left', toolbar: '#table-useradmin-admin'}
            , {field: 'id', width: 80, title: '用户ID', align: "center"}
            , {field: 'name', title: '用户名称'}
            , {field: 'username', title: '登录账号'}
            , {field: 'roleName', title: '角色'}
            , {field: 'email', title: '邮箱'}
            , {field: 'mobile', title: '手机号'}
            , {field: 'status', title: '状态', width: 80, templet: '#adStatus', align: 'center'}
            , {field: 'locked', title: '锁定状态', width: 80, templet: '#adLocked', align: 'center'}
            , {field: 'createTime', title: '创建时间'}
            , {field: 'modifyTime', title: '修改时间'}
        ]]
        , request: {
            pageName: 'page' //页码的参数名称
            , limitName: 'limit' //每页数据量的参数名称
        }
        , jump: function (obj, first) {
            if (!first) {
                page = obj.curr;
                getFirendList();
            }
        }
        , parseData: function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.count,
                "data": res.data
            }
        }
        , page: true
        , page: {
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
            , limit: 10
            , limits: [5, 10, 15, 50, 100]
            , groups: 4 //显示四个连续页码
            , first: '首页'
            , last: '尾页'
        }
        , text: {none:'无数据'}
    });

    //监听工具条
    table.on('tool(LAY-user-back-manage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此管理员？', function (index) {
                //console.log(obj.data);
                //obj.del();
                //提交 Ajax 成功后，静态更新表格中的数据
                $.ajax({
                    url: "/basicSystem/delAdmin",
                    type: "POST",
                    data: JSON.stringify(obj.data),
                    contentType: "application/json",
                    dataType: 'json',
                    beforeSend: function (loading) {
                        loading = layer.load(0, {shade: false});
                    },
                    success: function (res) {
                        if (res.code == "0") {
                            table.reload('LAY-user-back-manage'); //数据刷新
                            layer.msg("操作成功");
                        } else {
                            layer.msg(res.msg);
                        }
                        layer.closeAll('loading'); //关闭loading图标
                        layer.close(index); //关闭弹层
                    },
                    error: function () {
                        layer.msg("出现问题，请稍后重试！");
                        layer.closeAll('loading'); //关闭loading图标
                        //layer.close(index); //关闭弹层
                    }
                });
                layer.close(index);
            });

        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);
            //console.log(tr);
            //console.log(obj.data);
            window.pdata = obj.data;//为子窗口传值的变量
            //console.log(pdata);
            layer.open({
                type: 2
                , title: '编辑管理员'
                , content: '/basicSystem/editAdmin'
                , area: ['420px', '600px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submitID = 'editAdmin-submit-button'
                        , submit = layero.find('iframe').contents().find('#' + submitID);
                    //监听提交
                    iframeWindow.layui.form.on('submit(' + 'editAdmin-form' + ')', function (data) {
                        var field = data.field; //获取提交的字段
                        console.log("获取到的editAdmin提交字段:" + JSON.stringify(field));
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: "/basicSystem/editAdmin",
                            type: "POST",
                            data: JSON.stringify(field),
                            contentType: "application/json",
                            dataType: 'json',
                            beforeSend: function (loading) {
                                loading = layer.load(0, {shade: false});
                            },
                            success: function (res) {
                                if (res.code == "0") {
                                    table.reload('LAY-user-back-manage'); //数据刷新
                                    layer.msg("操作成功");
                                } else {
                                    layer.msg(res.msg);
                                }
                                layer.closeAll('loading'); //关闭loading图标
                                layer.close(index); //关闭弹层
                            },
                            error: function () {
                                layer.msg("出现问题，请稍后重试！");
                                layer.closeAll('loading'); //关闭loading图标
                                //layer.close(index); //关闭弹层
                            }
                        });
                        table.reload('LAY-user-front-submit'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            })
        }
    });

    //角色管理
    table.render({
        elem: '#LAY-user-back-role'
        , url: '/basicSystem/getAllRole'
        , method: 'POST'
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 80, title: 'ID', sort: true}
            , {field: 'name', title: '角色名'}
            , {field: 'roleKey', title: '标识符'}
            , {field: 'description', title: '具体描述'}
            , {field: 'status', title: '状态', templet: '#roleStatus', width: 80}
            , {field: 'createTime', title: '创建时间'}
            , {field: 'modifyTime', title: '修改时间'}
        ]]
        , request: {
            pageName: 'page' //页码的参数名称
            , limitName: 'limit' //每页数据量的参数名称
        }
        , jump: function (obj, first) {
            if (!first) {
                page = obj.curr;
                getFirendList();
            }
        }
        , parseData: function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.count,
                "data": res.data
            }
        }
        , page: true
        , page: {
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip']
            , limit: 10
            , limits: [5, 10, 15, 50, 100]
            , groups: 4 //显示四个连续页码
            , first: '首页'
            , last: '尾页'
        }
        , text: {none:'无数据'}
    });

    //监听工具条
    table.on('tool(LAY-user-back-role)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此角色？', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);

            layer.open({
                type: 2
                , title: '编辑角色'
                , content: '/basicSystem/editRole'
                , area: ['500px', '480px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submit = layero.find('iframe').contents().find("#LAY-user-role-submit");

                    //监听提交
                    iframeWindow.layui.form.on('submit(LAY-user-role-submit)', function (data) {
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        table.reload('LAY-user-back-role'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                , success: function (layero, index) {

                }
            })
        }
    });

    exports('useradmin', {})
});
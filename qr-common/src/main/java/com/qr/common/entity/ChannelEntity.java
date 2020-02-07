package com.qr.common.entity;

/**
 * @author:Jascon
 * @date:2020-02-03 22:11
 */
public class ChannelEntity extends UserEntity {
    private String extra;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return super.toString()+"\nChannelEntity{" +
                "extra='" + extra + '\'' +
                '}';
    }


    public static void main(String[] args) {
        ChannelEntity c=new ChannelEntity();
       c.setUsername("1123");
       c.setExtra("extra");
        System.out.println(c.toString());
    }
}

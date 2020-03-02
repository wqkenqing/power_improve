package test;

import operate.HbaseOperate;

import java.io.IOException;

public class HbaseTest {
    public static void main(String[] args) throws IOException {
        HbaseOperate operate = new HbaseOperate();
        operate.scanShow("meta:topics");
    }
}

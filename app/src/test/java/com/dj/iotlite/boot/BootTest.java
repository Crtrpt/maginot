package com.dj.iotlite.boot;

import org.junit.Test;

import static org.junit.Assert.*;

public class BootTest {

    @Test
    public void getClassByAnnotation() {
        Boot boot=new Boot();
        boot.getClassByAnnotation();
    }
}
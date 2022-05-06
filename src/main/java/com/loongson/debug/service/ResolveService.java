package com.loongson.debug.service;

import com.loongson.debug.entity.Head;

import java.io.File;


public interface ResolveService {

    public Head resolve(File file,int ltid,String logType);
}

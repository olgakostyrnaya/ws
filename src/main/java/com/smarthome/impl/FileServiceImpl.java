package com.smarthome.impl;

import com.smarthome.interfaces.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Olga on 16.05.2017.
 */

@Service
public class FileServiceImpl implements FileService {

    private String fileService;

    FileServiceImpl(){
    };

    @Override
    public List<String> ReadFromFile(String fileName) {
        return null;
    }

    @Override
    public void WriteToFile(String listValue){};

}

package com.smarthome.interfaces;

import java.util.List;

/**
 * Created by Olga on 16.05.2017.
 */

public interface FileService {

    List<String> ReadFromFile(String fileName);
    void WriteToFile(String listValue);

}

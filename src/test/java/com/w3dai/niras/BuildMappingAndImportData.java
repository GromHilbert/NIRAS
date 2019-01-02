package com.w3dai.niras;

import com.w3dai.DataImport.CreateIndexFromData;
import com.w3dai.DataImport.Json2es;

import java.io.IOException;

public class BuildMappingAndImportData {
    public static void main(String[] args) throws IOException, InterruptedException
    {
        CreateIndexFromData createIndexFromData = new CreateIndexFromData();
        createIndexFromData.CreateIndex();
        Json2es json2esAction = new Json2es();
        json2esAction.readJasonAndWriteToES("./DataSet/dataSet.txt");
    }
}

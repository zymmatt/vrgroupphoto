package com.example.admin.service;


import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.example.admin.mapper.ResourceMapper;
import com.example.admin.utils.blobstorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;



    public String gettempSAS(){
        return blobstorage.gettempSAS(blobstorage.getclient());
    }

    public String getVRphoto(String name){
        System.out.println(name);
        BlobContainerClient containerClient = blobstorage.getclient();
        BlobClient blobClient = containerClient.getBlobClient(name);
        if (blobClient.exists()) {
            // 找到资源了返回链接
            String accountName = blobstorage.accountName();
            String containerName = blobstorage.containerName();
            return String.format("https://%s.blob.core.windows.net/%s/%s?%s",
                    accountName, containerName, name, gettempSAS());
        }
        else{
            return "";
        }
    }

    public void submitCleanPhoto(MultipartFile file, String userid) throws IOException {
        String filename = String.format("%s_clean.%s",userid,"png");
        BlobContainerClient containerClient = blobstorage.getclient();
        BlobClient blobClient = containerClient.getBlobClient(filename);
        blobClient.deleteIfExists();
        byte[] imageBytes = file.getBytes();
        blobClient.upload(BinaryData.fromBytes(imageBytes));
        String accountName = blobstorage.accountName();
        String containerName = blobstorage.containerName();
        String photoURL = String.format("https://%s.blob.core.windows.net/%s/%s",
                accountName,containerName,filename);
        resourceMapper.submitCleanPhoto(photoURL,userid);

    }

    public String downloadPhoto(int id) {
        String URL = resourceMapper.getURL(id);
        return String.format("%s?%s", URL, gettempSAS());
    }
}

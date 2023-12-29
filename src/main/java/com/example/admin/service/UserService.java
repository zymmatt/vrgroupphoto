package com.example.admin.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.example.admin.entity.User.User;
import com.example.admin.mapper.UserMapper;
import com.example.admin.utils.datetime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.example.admin.utils.string_proc;
import com.example.admin.utils.blobstorage;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
        List<User> Userlist = userMapper.findAll();
        for (User tempuser:Userlist){
            String tempURL = tempuser.getPhotoURL();
            if (tempURL == null){
                tempuser.setPhotoURL("");
            }
            else {
                tempuser.setPhotoURL(String.format("https://vrstate.inventec.com/vrphoto-admin/#/user/%s/",string_proc.extractFileName(tempURL)));
            }
            long timestamp = tempuser.getTrigger_timestamp();
            if (timestamp==0){
                tempuser.setTrigger_time("");
            }
            else{
                tempuser.setTrigger_time(datetime.timestamp2str(timestamp));
            }
        }
        return Userlist;
    }

    public List<User> findAllWaiting() { return userMapper.findAllwaiting();}

    public User getNext(){
        return userMapper.getNext();
    }

    public int createUser(User user){
        String name = user.getName();
        User tempuser = userMapper.findName(name);
        if (tempuser == null){
            user.setUserid(string_proc.generateRandomString(8));
            userMapper.createUser(user);
            return user.getId();
        }
        else {
            return -1;
        }
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public void submitPhoto(MultipartFile file, String userid) throws IOException {
        String filename = String.format("%s.%s",userid,"png");
        BlobContainerClient containerClient = blobstorage.getclient();
        BlobClient blobClient = containerClient.getBlobClient(filename);
        blobClient.deleteIfExists();
        byte[] imageBytes = file.getBytes();
        blobClient.upload(BinaryData.fromBytes(imageBytes));
        String accountName = blobstorage.accountName();
        String containerName = blobstorage.containerName();
        String photoURL = String.format("https://%s.blob.core.windows.net/%s/%s",
                accountName,containerName,filename);
        long trigger_timestamp = datetime.getcurrentSecondTimeStamp();
        userMapper.submitPhoto(photoURL, userid, trigger_timestamp);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }
}

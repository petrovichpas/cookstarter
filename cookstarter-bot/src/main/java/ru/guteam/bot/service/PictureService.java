package ru.guteam.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guteam.bot.sender.PictureSender;

@Service
public class PictureService {
    private PictureSender pictureSender;

    @Autowired
    public PictureService(PictureSender pictureSender) {
        this.pictureSender = pictureSender;
    }
}

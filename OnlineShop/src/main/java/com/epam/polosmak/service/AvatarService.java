package com.epam.polosmak.service;

import com.epam.polosmak.web.bean.Avatar;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface AvatarService {
    void saveAvatar(Avatar avatar, String fileName) throws IOException;

    BufferedImage getAvatar(String avatarFile) throws IOException;

    boolean isImageExist(String avatarFileName) throws IOException;
}

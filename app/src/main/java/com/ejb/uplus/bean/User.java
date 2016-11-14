package com.ejb.uplus.bean;

import java.util.UUID;

/**
 * Created by John on 10/31/2016.
 */

public class User
{
    private UUID uuid = UUID.randomUUID();
    private long userId;
    private String name;
}

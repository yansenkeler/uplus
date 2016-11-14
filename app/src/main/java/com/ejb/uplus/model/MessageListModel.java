package com.ejb.uplus.model;

import com.cl.core.MVPFrame.BaseModel;
import com.ejb.uplus.bean.Message;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by John on 11/7/2016.
 */

public class MessageListModel extends BaseModel
{
    public ArrayList<Message> getMessageData()
    {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("title1", "For months now, opponents of the $3.8 billion pipeline - which is slated to move oil from North Dakota through South" +
                " Dakota and Iowa to a shipping point in Illinois - have been camping near the confluence of the Missouri and Cannonball rivers. They worry the p" +
                "roject will disrupt cultural artifacts and hurt drinking water sources on the Standing Rock Sioux’s nearby reservation and farther downstream be" +
                "cause the pipeline will cross the Missouri River.The Texas-based company building the pipeline, Energy Transfer Partners, insists the project is" +
                " safe. The tribe is fighting the pipeline’s permitting process in federal court. Since the number of protesters soared in August in North Dakota" +
                ", donations started rolling in more frequently and more than 400 people have been arrested - including more than 140 on Thursday when officers e" +
                "victed protesters camping on private land recently acquired by Energy Transfer Partners.But running a camp - and readying it for North Dakota’s " +
                "brutal winter - isn’t cheap. The account Wicasa set up has only about $100,000 left as of Friday night, according to LaDonna Brave Bull Allard, " +
                "a tribal historian and preservation employee. She provided family land for use in the original camp, Sacred Stone, in April and still houses dem" +
                "onstrators.",
                new Date(System.currentTimeMillis())));
        messages.add(new Message("title2", "I have a table with form inputs, at the bottom of the table, I have an \"Add row\" button. My issue is I'm starting in" +
                " an input box, and hitting tab to move to this <a> add row button. When I hit enter with the link ",
                new Date(System.currentTimeMillis())));
        messages.add(new Message("title3", "0 down vote favorite I have a workbook that contains about 150 worksheets. The first worksheet is a table/list of infor" +
                "mation that is about 150 rows long and 16 columns wide and is named \"log\". In col. 'j' of the ",
                new Date(System.currentTimeMillis())));
        messages.add(new Message("title4", "Good evening. I have been told to use an integer array and insert elements into it, which is relatively simple. The dif" +
                "ficult part is having to expand the array and retain its existing elements, doing all this within",
                new Date(System.currentTimeMillis())));
        messages.add(new Message("title5", "favorite Good evening. I have been told to use an integer array and insert elements into it, which is relatively simple." +
                " The difficult part is having to expand the array and retain its existing elements, doing all th",
                new Date(System.currentTimeMillis())));
        messages.add(new Message("title6", "Good evening. I have been told to use an integer array and insert elements into it, which is relatively simple. The diff" +
                "icult part is having to expand the array and retain its existing elements, doing all this withi",
                new Date(System.currentTimeMillis())));
        messages.add(new Message("title7", "I have considered using ArrayList, where ArrayList.add is O(1), as long as the given/default length hasn't been met, othe" +
                "rwise it is O(n) in order to reallocate a new ArrayList with double the space, and copy the o",
                new Date(System.currentTimeMillis())));
        messages.add(new Message("title8", "To keep listeners to a minimum, I am using one change listener on form. However, the event only fires if I change the firs" +
                "t column/set of radio buttons. It does not fired on the rest of the columns of radio buttons.",
                new Date(System.currentTimeMillis())));
        return messages;
    }
}

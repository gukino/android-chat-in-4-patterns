package nju.androidchat.client.hw1;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import kotlin.text.Regex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nju.androidchat.client.ClientMessage;
import nju.androidchat.client.R;

@AllArgsConstructor
public class Mvp0TalkPresenter implements Mvp0Contract.Presenter {

    private Mvp0Contract.Model mvp0TalkModel;
    private Mvp0Contract.View iMvp0TalkView;

    @Getter
    private List<ClientMessage> clientMessages;

    @Override
    public void sendMessage(String content) {
        ClientMessage clientMessage = mvp0TalkModel.sendInformation(content);
        refreshMessageList(clientMessage);
    }

    @Override
    public void receiveMessage(ClientMessage clientMessage) {

        refreshMessageList(clientMessage);
    }

    @Override
    public String getUsername() {
        return mvp0TalkModel.getUsername();
    }


    public Bitmap getLocalImage(String url){
        if (url != null) {

            System.out.println(url);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(url);
                return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                if(fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fis = null;
                }
            }
        } else {
            return null;
        }
    }

    private void refreshMessageList(ClientMessage clientMessage) {
        Regex regex = new Regex("\\!\\[\\]\\(\\{.*\\}\\)");

        String str = clientMessage.getMessage();
        if (regex.matches(str)){
            try{
                String s =str.split("\\{")[1];
                String url = s.substring(0,s.length()-2);

                String url1 = "https://aod-image-material.cdn.bcebos.com/5/pic/0f70f8569f25149025fc80f819f51cd0.jpg";

//                ImageUtils.setImageBitmap(url1);
//                Bitmap bitmap = ImageUtils.getBitmap();

            }catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
            System.out.println(str);
//            System.out.println(clientMessage.getMessage().split("{"));


//            System.out.println(url);
//            System.out.println(getLocalImage(url));
        }
        clientMessages.add(clientMessage);
        iMvp0TalkView.showMessageList(clientMessages);
    }

    //撤回消息，Mvp0暂不实现
    @Override
    public void recallMessage(int index0) {

    }

    @Override
    public void start() {

    }
}

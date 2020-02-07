package com.nf.skateboard.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SendVerificationCode {

    public String sendMessage(String phone,String randomNumber) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "密钥", "密钥");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "二学期项目");
        request.putQueryParameter("TemplateCode", "SMS_172223057");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+randomNumber+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String data = response.getData();
            return data.substring(data.indexOf("Message")+10, data.indexOf(",")-1);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        SendVerificationCode code = new SendVerificationCode();
        System.out.println("发送短信！！");
        code.sendMessage("18998280320","123456");
    }
}

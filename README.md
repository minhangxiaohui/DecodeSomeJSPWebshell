# DecodeSomeJSPWebshell
利用java Swing 简单写的一个jspWebshell 解密工具：
  支持 冰蝎2，冰蝎3，哥斯拉
## 使用案例：
如下为冰蝎3通信时产生的流量，对齐进行解密
![image](https://user-images.githubusercontent.com/39674723/196080564-db91433d-44e4-41cb-9865-85464066c90a.png)
使用工具，输入密码，对其进行解密：
![image](https://user-images.githubusercontent.com/39674723/196080624-d058cdf1-7160-405f-bfab-5dacdf60e579.png)
![image](https://user-images.githubusercontent.com/39674723/196080633-b743a516-90d5-4752-b2d8-707924ddf02d.png)
还原出使用的payload 恶意类

哥斯拉的解密同理 案例如下，注意注入的密码为shell里面定义的的pass也就是密码，输入的密钥为shell里面定义的xc 也就是密钥的md5的前16位：
案例如下：
![image](https://user-images.githubusercontent.com/39674723/232743463-e42e27aa-d583-4db6-b58f-3cc864fa3e51.png)
![image](https://user-images.githubusercontent.com/39674723/232744212-c0bb203f-0d2a-4955-9df5-ec8efdb03a4a.png)

  
# 问题： 
## 目前存在的问题：
  因为冰蝎webshell管理工具的jsp马其本身在实现的时候：客户端发起命令执行的请求流量是以加密后base64编码传输的，但是服务端发回的命令执行的结果的响应流量的传输内容是直接为AES加密之后的
二进制流，因为我们通过wireshark或者bp抓到的流量中，展现形式通常以看见字符的解码方式去解码，所以导致我们看到的都是一些乱码。因此在实现响应流解密的时候，这里规定要使用响应内容的16进制，
内部的话从16进制还原成bytes ，然后实现AES解密，下面这张图是我们在尝试解密冰蝎响应流量时，要get的部分:

![avatar](./forreadme1.png)

这里之后还要想办法优化下，暂时先这样吧

## 解密中存在的问题：
  冰蝎2**首次**持续化通信阶段，服务端响应流量解密不出来，其他正常；
  
  冰蝎3也是一样，没有之前的密钥协商，首次就是第一次，所以第一次的响应流量解密失败，其他正常；
  
  哥斯拉请求响应流量还原正常；

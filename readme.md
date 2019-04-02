# Readme

This project is a easy and very simple Rewrite URL library por JSF applications. 
It allows you to create friendly url in your JSF applications. It maps simple URL to its "nice to see" new urls in a 1:1 way, doesn´t have functionally for patterns or more complex ways to map the urls.

## How to use it ? 
- First download the project and install it!


    mvn clean install


- Then add the dependency in your application


        <dependency>
            <groupId>com.orthanc.commons</groupId>
            <artifactId>simple-rewriteurl-jsf</artifactId>
            <version>1.0</version>
        </dependency>


The application already has a `faces-config.xml` file to register the View Handler used during the rewrite url logic.
So, it's not necessary to add the View Handler in your application.

- Let´s start mapping the URL to your new friendly URLS. You need to create a new `rewrite-config.xml` file,
and add every one of the url mappings.

 
    <?xml version="1.0" encoding="UTF-8"?>
    <rewrite-config>

        <url-mapping id="index">
            <pattern value="/signin"/>
            <view-id value="/index.xhtml"/>
        </url-mapping>

        <url-mapping id="home">
            <pattern value="/home"/>
            <view-id value="/pages/home.xhtml"/>
        </url-mapping>

        <url-mapping id="about">
            <pattern value="/about"/>
            <view-id value="/pages/about.xhtml"/>
        </url-mapping>

        <url-mapping id="customer_list">
            <pattern value="/customers"/>
            <view-id value="/pages/customer/list.xhtml"/>
        </url-mapping>

        <url-mapping id="customer_detail">
            <pattern value="/customer/detail"/>
            <view-id value="/pages/customer/detail.xhtml"/>
        </url-mapping>

        <url-mapping id="customer_edit">
            <pattern value="/customer/new"/>
            <view-id value="/pages/customer/new.xhtml"/>
        </url-mapping>

        <url-mapping id="settings">
            <pattern value="/settings"/>
            <view-id value="/pages/settings/panel.xhtml"/>
        </url-mapping>

        <url-mapping id="userprofile">
            <pattern value="/user/profile"/>
            <view-id value="/pages/profile/userprofile.xhtml"/>
        </url-mapping>
    </rewrite-config>


That's all, as simple as do that for every one of the URL you want to **rewrite**





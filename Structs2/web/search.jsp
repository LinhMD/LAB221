<%-- 
    Document   : search
    Created on : Nov 2, 2020, 7:58:28 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h4>
            Welcome, <s:property value="%{#session.USERNAME}"/>
            <s:property value="username"/>
            <s:property value="%{#attr.USERNAME}"/>
        </h4>
        <a href="">Sign Out</a>
        <h1>Search Page</h1>
        <s:form action="searchLastName">
            <s:textfield name="searchValue" label="Search value"/>
            <s:submit value="Search"/>
        </s:form>
        
        <s:if test="%{!searchValue.isEmpty()}">
            
        </s:if>
    </body>
</html>

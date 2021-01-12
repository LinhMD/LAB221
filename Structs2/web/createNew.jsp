<%-- 
    Document   : createNEw
    Created on : Nov 4, 2020, 8:16:18 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new Account</title>
        <s:head/>
    </head>
    <body>
        <h2>Create new Account</h2>
        <s:form action="create">
            <s:textfield name="userName" label="Username"/>
            <s:label value="6-30 chars"/>
            <s:password name="passWord" label="Password"/>
            <s:label value="6-20 chars"/>
            <s:password name="comfirm" label="Comfirm"/>
            <s:label value="6-20 chars"/>
            <s:textfield name="fullName" label="Fullname"/>
            <s:label value="6-50 chars"/>
            <s:checkbox name="role" label="Role"/>
            <input type="submit" value="Sign Up"/>
        </s:form>
        <s:if test="exception.message.contains('duplicate')">
            <s:property value="userName"/> is existed!!!
        </s:if>
    </body>
</html>

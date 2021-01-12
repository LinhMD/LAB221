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
            <s:form action="logout">
                <s:submit value="Log out"/>
            </s:form>
        <h1>Search Page</h1>
        <s:form action="searchLastName">
            <s:textfield name="searchValue" label="Search value"/>
            <s:submit value="Search"/>
        </s:form>
        
        <s:if test="%{!searchValue.isEmpty()}">
            <s:if test="%{accountList != null}">
                
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>password</th>
                            <th>Fullname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead> 
                    <tbody>
                        <s:iterator value="accountList" status="counter">
                        <s:form action="update" method="GET" theme="simple">
                        <tr>
                            <td><s:property value="%{#counter.count}"/></td>
                            <td>
                                <s:property value="userName"/>
                                <s:hidden name="userName" value="%{userName}"/>
                            </td>
                            <td>
                                <s:textfield name="passWord" value="%{passWord}"/>
                                
                            </td>
                            <td><s:textfield name="fullName" value="%{fullName}"/></td>
                            <td>
                                <s:checkbox name="admin" value="%{role}"/>
                            </td>
                            <td>
                                <s:url id="urlRewritng" action="delete">
                                    <s:param name="pk" value="userName"/>
                                    <s:param name="lastSearchValue" value="%{searchValue}"/>
                                </s:url>
                                <s:a href="%{urlRewritng}">delete</s:a>
                            </td>
                            <td>
                            <s:submit value="Update"/>
                            <s:hidden name="lastSearchValue" value="%{searchValue}"/>
                            </td>
                        </tr>
                        </s:form>
                        </s:iterator>
                    </tbody>
                   
                </table>
                
            </s:if>
            <s:else>
                <h2>Not found!!!</h2>
            </s:else>
        </s:if>
    </body>
</html>

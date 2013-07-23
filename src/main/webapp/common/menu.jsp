<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="nav-collapse collapse">
<ul class="nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <menu:displayMenu name="MainMenu"/>
    <menu:displayMenu name="UserMenu"/>
      <!--Brand-START-->
    <menu:displayMenu name="BrandMenu"/>
    <!--Brand-END-->
    <!--Accessories-START-->
    <menu:displayMenu name="AccessoriesMenu"/>
    <!--Accessories-END-->
    <!--Employee-START-->
    <menu:displayMenu name="EmployeeMenu"/>
    <!--Employee-END-->
    <!--Accountrecord-START-->
    <menu:displayMenu name="AccountrecordMenu"/>
    <!--Accountrecord-END-->
    <!--Customer-START-->
    <menu:displayMenu name="CustomerMenu"/>
    <!--Customer-END-->
    <!--Purchase-START-->
    <menu:displayMenu name="PurchaseMenu"/>
    <!--Purchase-END-->
    <!--ServiceOrder-START-->
    <menu:displayMenu name="ServiceOrderMenu"/>
    <!--ServiceOrder-END-->
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    
</ul>
</div>
</menu:useMenuDisplayer>

<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="employeeList.title"/></title>
    <meta name="menu" content="EmployeeMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="employeeList.heading"/></h2>

    <form method="post" action="${ctx}/employees" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="employeeList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editEmployee'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="employees" class="table table-condensed table-striped table-hover" requestURI="" id="employeeList" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="editEmployee" media="html"
            paramId="id" paramProperty="id" titleKey="employee.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="employee.id"/>
        <display:column property="age" sortable="true" titleKey="employee.age"/>
        <display:column property="email" sortable="true" titleKey="employee.email"/>
        <display:column property="employeeNo" sortable="true" titleKey="employee.employeeNo"/>
        <display:column property="gender" sortable="true" titleKey="employee.gender"/>
        <display:column property="nick" sortable="true" titleKey="employee.nick"/>
        <display:column property="qq" sortable="true" titleKey="employee.qq"/>
        <display:column property="trueName" sortable="true" titleKey="employee.trueName"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="employeeList.employee"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="employeeList.employees"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="employeeList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="employeeList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="employeeList.title"/>.pdf</display:setProperty>
    </display:table>
</div>

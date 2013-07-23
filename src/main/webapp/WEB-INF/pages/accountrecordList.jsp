<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="accountrecordList.title"/></title>
    <meta name="menu" content="AccountrecordMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="accountrecordList.heading"/></h2>

    <form method="post" action="${ctx}/accountrecords" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="accountrecordList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editAccountrecord'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="accountrecords" class="table table-condensed table-striped table-hover" requestURI="" id="accountrecordList" export="true" pagesize="25"  defaultsort="1" sort="list" defaultorder="descending">
        <display:column property="id" sortable="true" href="editAccountrecord" media="html"
            paramId="id" paramProperty="id" titleKey="accountrecord.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="accountrecord.id"/>
        <display:column property="charge" sortable="true" titleKey="accountrecord.charge"/>
        <display:column property="createTime"  sortable="true" titleKey="accountrecord.createTime" format="{0,date,yyyy-MM-dd}"/>
        
        <display:column property="type.name" sortable="true" titleKey="accountrecord.type"/>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="accountrecordList.accountrecord"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="accountrecordList.accountrecords"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="accountrecordList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="accountrecordList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="accountrecordList.title"/>.pdf</display:setProperty>
    </display:table>
</div>

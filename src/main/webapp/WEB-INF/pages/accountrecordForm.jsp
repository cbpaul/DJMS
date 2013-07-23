<%@ include file="/common/taglibs.jsp"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <title><fmt:message key="accountrecordDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='accountrecordDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="accountrecordList.accountrecord"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="accountrecordDetail.heading"/></h2>
    <fmt:message key="accountrecordDetail.message"/>
</div>

<div class="span7">
    <s:form id="accountrecordForm" action="saveAccountrecord" method="post" validate="true" cssClass="well form-horizontal">
            <s:hidden key="accountrecord.id"/>
         <s:select name="accountrecord.type" list="accountrecordTypes" listKey="name()" listValue="name" key = "accountrecord.type" onchange="changeType(this)"  headerKey="" headerValue="请选择..."/>
         <s:select name="accountrecord.brand.id" list="brandList" listKey="id" listValue="name" key = "select.brand" headerKey="" headerValue="请选择..." onchange="brandChange(this)"/>
         <s:select name="accountrecord.accessories.id" list="accessorieList" listKey="id" listValue="name" key = "select.accessories" headerKey="" headerValue="请选择..."/>
         <s:textfield key="accountrecord.charge" required="false" maxlength="255" />
		
        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty accountrecord.id}">
                <s:submit type="button" cssClass="btn btn-warning" method="delete" key="button.delete"
                    onclick="return confirmMessage(msgDelConfirm)" theme="simple">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
    </s:form>
</div>

<script type="text/javascript">

    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['accountrecordForm']).focus();
        changeType($("#accountrecordForm_accountrecord_type"));
    });
    function changeType(node){
    	  if($(node).val()!="REPLACE"){
          	$("#accountrecordForm_accountrecord_brand_id").parent().parent("fieldset").hide();
          	$("#accountrecordForm_accountrecord_accessories_id").parent().parent("fieldset").hide();
          }else{
        	 $("#accountrecordForm_accountrecord_brand_id").parent().parent("fieldset").show();
             $("#accountrecordForm_accountrecord_accessories_id").parent().parent("fieldset").show();
          }
    }
    function brandChange(brandNode){
    	$.get("${ctx}/accessoriesListByBrand?brandId="+$(brandNode).val(),
    			function(data){
    				$("#accountrecordForm_accountrecord_accessories_id").empty();
    				$("#accountrecordForm_accountrecord_accessories_id").append("<option value=''>请选择...");
    				for(var i=0 ;i <data.length ; i++){
    					$("#accountrecordForm_accountrecord_accessories_id").append("<option value="+data[i].id+">"+data[i].name);
    				}
    			},
    		"json"
    	);
    }
  
</script>

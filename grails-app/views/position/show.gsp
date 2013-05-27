
<%@ page import="edu.nps.fauxgame.Position" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'position.label', default: 'Position')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-position" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-position" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list position">
			
				<g:if test="${positionInstance?.state}">
				<li class="fieldcontain">
					<span id="state-label" class="property-label"><g:message code="position.state.label" default="State" /></span>
					
						<span class="property-value" aria-labelledby="state-label"><g:fieldValue bean="${positionInstance}" field="state"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${positionInstance?.gameInstance}">
				<li class="fieldcontain">
					<span id="gameInstance-label" class="property-label"><g:message code="position.gameInstance.label" default="Game Instance" /></span>
					
						<span class="property-value" aria-labelledby="gameInstance-label"><g:link controller="gameInstance" action="show" id="${positionInstance?.gameInstance?.id}">${positionInstance?.gameInstance?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${positionInstance?.playerIdent}">
				<li class="fieldcontain">
					<span id="playerIdent-label" class="property-label"><g:message code="position.playerIdent.label" default="Player Ident" /></span>
					
						<span class="property-value" aria-labelledby="playerIdent-label"><g:fieldValue bean="${positionInstance}" field="playerIdent"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${positionInstance?.roleParam}">
				<li class="fieldcontain">
					<span id="roleParam-label" class="property-label"><g:message code="position.roleParam.label" default="Role Param" /></span>
					
						<span class="property-value" aria-labelledby="roleParam-label"><g:fieldValue bean="${positionInstance}" field="roleParam"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${positionInstance?.id}" />
					<g:link class="edit" action="edit" id="${positionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

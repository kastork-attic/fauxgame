
<%@ page import="edu.nps.fauxgame.GameInstance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameInstance.label', default: 'GameInstance')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gameInstance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gameInstance" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gameInstance">
			
				<g:if test="${gameInstanceInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="gameInstance.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${gameInstanceInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstanceInstance?.gameTitle}">
				<li class="fieldcontain">
					<span id="gameTitle-label" class="property-label"><g:message code="gameInstance.gameTitle.label" default="Game Title" /></span>
					
						<span class="property-value" aria-labelledby="gameTitle-label"><g:link controller="gameTitle" action="show" id="${gameInstanceInstance?.gameTitle?.id}">${gameInstanceInstance?.gameTitle?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstanceInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="gameInstance.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${gameInstanceInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstanceInstance?.lobbyServer}">
				<li class="fieldcontain">
					<span id="lobbyServer-label" class="property-label"><g:message code="gameInstance.lobbyServer.label" default="Lobby Server" /></span>
					
						<span class="property-value" aria-labelledby="lobbyServer-label"><g:link controller="lobbyServer" action="show" id="${gameInstanceInstance?.lobbyServer?.id}">${gameInstanceInstance?.lobbyServer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstanceInstance?.position}">
				<li class="fieldcontain">
					<span id="position-label" class="property-label"><g:message code="gameInstance.position.label" default="Position" /></span>
					
						<g:each in="${gameInstanceInstance.position}" var="p">
						<span class="property-value" aria-labelledby="position-label"><g:link controller="position" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameInstanceInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameInstanceInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

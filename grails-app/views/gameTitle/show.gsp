
<%@ page import="edu.nps.fauxgame.GameTitle" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameTitle.label', default: 'GameTitle')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gameTitle" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gameTitle" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gameTitle">
			
				<g:if test="${gameTitleInstance?.uriToken}">
				<li class="fieldcontain">
					<span id="uriToken-label" class="property-label"><g:message code="gameTitle.uriToken.label" default="Uri Token" /></span>
					
						<span class="property-value" aria-labelledby="uriToken-label"><g:fieldValue bean="${gameTitleInstance}" field="uriToken"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameTitleInstance?.displayName}">
				<li class="fieldcontain">
					<span id="displayName-label" class="property-label"><g:message code="gameTitle.displayName.label" default="Display Name" /></span>
					
						<span class="property-value" aria-labelledby="displayName-label"><g:fieldValue bean="${gameTitleInstance}" field="displayName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameTitleInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="gameTitle.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${gameTitleInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameTitleInstance?.gameInstances}">
				<li class="fieldcontain">
					<span id="gameInstances-label" class="property-label"><g:message code="gameTitle.gameInstances.label" default="Game Instances" /></span>
					
						<g:each in="${gameTitleInstance.gameInstances}" var="g">
						<span class="property-value" aria-labelledby="gameInstances-label"><g:link controller="gameInstance" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${gameTitleInstance?.gameVersion}">
				<li class="fieldcontain">
					<span id="gameVersion-label" class="property-label"><g:message code="gameTitle.gameVersion.label" default="Game Version" /></span>
					
						<span class="property-value" aria-labelledby="gameVersion-label"><g:fieldValue bean="${gameTitleInstance}" field="gameVersion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameTitleInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="gameTitle.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${gameTitleInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameTitleInstance?.numberPositions}">
				<li class="fieldcontain">
					<span id="numberPositions-label" class="property-label"><g:message code="gameTitle.numberPositions.label" default="Number Positions" /></span>
					
						<span class="property-value" aria-labelledby="numberPositions-label"><g:fieldValue bean="${gameTitleInstance}" field="numberPositions"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameTitleInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameTitleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

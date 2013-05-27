<%@ page import="edu.nps.fauxgame.GameTitle" %>



<div class="fieldcontain ${hasErrors(bean: gameTitleInstance, field: 'uriToken', 'error')} required">
	<label for="uriToken">
		<g:message code="gameTitle.uriToken.label" default="Uri Token" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="uriToken" required="" value="${gameTitleInstance?.uriToken}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameTitleInstance, field: 'displayName', 'error')} ">
	<label for="displayName">
		<g:message code="gameTitle.displayName.label" default="Display Name" />
		
	</label>
	<g:textField name="displayName" value="${gameTitleInstance?.displayName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameTitleInstance, field: 'gameInstances', 'error')} ">
	<label for="gameInstances">
		<g:message code="gameTitle.gameInstances.label" default="Game Instances" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${gameTitleInstance?.gameInstances?}" var="g">
    <li><g:link controller="gameInstance" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="gameInstance" action="create" params="['gameTitle.id': gameTitleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'gameInstance.label', default: 'GameInstance')])}</g:link>
</li>
</ul>

</div>


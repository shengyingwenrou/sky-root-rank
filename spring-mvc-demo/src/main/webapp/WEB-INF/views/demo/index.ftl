<!DOCTYPE HTML>
<html>
<head>
    <title>spring mvc</title>
</head>


<body >
${fremmarker!} Hello World!

<#if userList??>
   <#list userList as user>
      ${user.id!}_${user.username!}
   </#list>
</#if>

</body>
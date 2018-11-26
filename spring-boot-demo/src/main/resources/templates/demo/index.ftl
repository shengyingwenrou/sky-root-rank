<!DOCTYPE HTML>
<html>
<head>
    <title>spring boot fremmarker</title>
</head>


<body>

<#if demo??>
  ${demo.name!}
</#if>
spring boot fremmarker welcome

<br>
map <br>

<#if maps??>
<#list maps?keys as key>
   key="${key}"> value=${maps[key].id!} name=${maps[key].name!}<br>
</#list>
</#if>

</body>
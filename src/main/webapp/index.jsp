<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Hello World!!</h2>
<s:form id="btn_sub" method="post" enctype="multipart/form-data" action="file/upload">
    <label>profile pictrue</label>
    <input type="file" name="profileName" accept="image/jpeg,image/png,image/jpg"/><br/>
    <button onclick="submitform();">present</button>
</s:form>
</body>
<script type="text/javascript">
    function submitform() {
        $("#btn_sub").submit();
    }
</script>
</html>

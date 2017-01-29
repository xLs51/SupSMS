$(document).ready(function()
{
	var query = window.location.search.substring(1);
    var vars = query.split("&");
    
    for (var i=0; i < vars.length; i++)
    {
        var pair = vars[i].split("=");
        if(pair[0] == "id")
        {
        	if($("input[value='" + pair[1] + "']").length)
        	{
        		$("input[name='invoice']").prop("checked", false);
        		
        		$("input[value='" + pair[1] + "']").prop("checked", true);
        	}
        }
    }
});
<html>
<head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
    $(document).ready(function()
    {

	// Variable to store your files
	var files = "";

	// Add events
	$('input[type=file]').on('change', prepareUpload);

	// Grab the files and set them to our variable
	function prepareUpload(event)
	{
	    files = event.target.files;
	    console.log(files);
	}

	$('#form').on('submit', uploadFiles);

	// Catch the form submit and upload the files
	function uploadFiles(event)
	{

	    event.stopPropagation(); // Stop stuff happening
	    event.preventDefault(); // Totally stop stuff happening 

	    var data = new FormData();
	    $.each(files, function(key, value)
	    {
		data.append("file", value);
	    });

	    $.ajax({
		url : '/FileUpload/singleSaveWithoutDescription',
		type : 'POST',
		data : data,
		cache : false,
		dataType : 'text', // The type of data that you're expecting back from the server.
		processData : false, // Don't process the files
		contentType : false, // Set content type to false as jQuery will tell the server its a query string request
		success : function(data, textStatus, jqXHR)
		{
		    if (typeof data.error === 'undefined')
		    {
			alert('File uploaded successfully');
			// Success so call function to process the form
			//submitForm(event, data);
		    }
		    else
		    {
			alert('Failure 2');
			// Handle errors here
			console.log('ERRORS: ' + data.error);
		    }
		},
		error : function(jqXHR, textStatus, errorThrown)
		{
		    alert('Failure 1');
		    // Handle errors here
		    console.log('ERRORS: ' + textStatus);
		    console.log('ERROR THROWN: ' + errorThrown);
		    console.log('ERRORS  desc: ' + jqXHR);
		    // STOP LOADING SPINNER
		}
	    });
	}

    });
</script>
</head>

<body>
	<h1>Single File Upload</h1>
	
	<h2>Without ajax</h2>
	<form method="post" enctype="multipart/form-data" action="singleSave">
		Upload File: <input type="file" name="file"> <br /> <br />
		Description: <input type="text" name="desc" /> <br /> <br /> <input
			type="submit" value="Upload">
	</form>

	<h2>With ajax</h2>
	<!-- action="singleSave" -->
	<form id="form" method="post" enctype="multipart/form-data">
		Upload File: <input type="file" name="file"> <br /> <br />
		Description: <input type="text" name="desc" /> <br /> <br /> <input
			type="submit" value="Upload">
	</form>
</body>
</html>

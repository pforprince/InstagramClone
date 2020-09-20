function checkUsername() {
    let username = $("#username").val();
    console.log("printing the usernmae ---------------" + username);
    data = {
        username: username
    };
    $.ajax({
        url: "UniqueUsername",
        data: data,
        success: function (data, textStatus, jqXHR) {
            console.log("printing data receiving from servlet");
            console.log(data);
            if (data.trim() === "available") {
                console.log("in the if statement of js");
                $("#status").removeClass("d-none");
                $("#submit-btn").removeClass("disabled");
                document.getElementById("status").innerHTML = "Available";

            } else if (data.trim() === "unavailable")
            {
                console.log("in the else statement of js");
                $("#status").removeClass("d-none");
                $("#status").html("UnAvailable");
                $("#submit-btn").addClass("disabled");

            } else {
                console.log("in the third else statement");
            }
        }
    }
    );
}


let liked = 'fa-heart';
let unliked = 'fa-heart-o';
function operation(postId) {
    let t = $('#likeunlike' + postId).hasClass(unliked);
    console.log(t);
    if (t)
    {
        console.log("in liked part")
        data = {
            postId: postId,
            operation: "Like"
        }
        $.ajax({
            url: "Like",
            data: data,
            success: function (data, textStatus, jqXHR) {
                let servletdata = data.split(";");
                let likesCount = servletdata[1];
                let status = servletdata[0].trim();
                console.log(likesCount);
                if (status === 'like') {
                    $('#likeunlike' + postId).removeClass(unliked).addClass(liked);
                } else {
                    $('#likeunlike' + postId).removeClass(liked).addClass(unliked);
                }

                document.getElementById("likesCount" + postId).innerHTML = likesCount;

                console.log("like saved");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });

    } else
    {
        const data = {
            postId: postId,
            operation: "Unlike"
        }

        $.ajax({
            url: "Like",
            data: data,
            success: function (data, textStatus, jqXHR) {
                let servletdata = data.split(";");
                let likesCount = servletdata[1];
                let status = servletdata[0].trim();
                console.log(likesCount);

                console.log(data);
                if (status === 'unlike') {
                    $('#likeunlike' + postId).removeClass(liked).addClass(unliked);
                } else {
                    $('#likeunlike' + postId).removeClass(unliked).addClass(liked);
                }
                document.getElementById("likesCount" + postId).innerHTML = likesCount;

                console.log("unlike saved");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown)
            }
        })

    }
}
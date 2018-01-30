String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".comment-writer input[type=submit]").on("click", addAnswer);

function addAnswer(e) {
    e.preventDefault();

    var queryString = $(".comment-writer").serialize();

    $.ajax({
        type : 'post',
        url : $(".comment-writer").attr("action"),
        data : queryString,
        dataType : 'json',
        error: function () {
            console.log('failure');
        },
        success : function (data, status) {
            var commentTemplate = $("#commentTemplate").html();
            var template = commentTemplate.format(data.author.userId,
                                            getDateString(data.createdDate),
                                             data.content,
                                              data.question.id,
                                               data.id);
            $(".qna-comment-slipp-articles").prepend(template);
            $("textarea[name=content]").val("");

            var count = parseInt($(".qna-comment-count strong").html());
            $(".qna-comment-count strong").text(count + 1);
        }
    });

    function getDateString(timestamp){
        const date = new Date(timestamp);
        let month = date.getMonth() + 1;
        if (month < 10) month = "0" + month;
        return date.getFullYear()
            + "-" + month
            + "-" + date.getDate()
            + " " + date.toTimeString().split(" ")[0]
            + "." + date.getMilliseconds();
    }
}

$(".qna-comment-slipp-articles").on("click", ".delete-comment-form button[type='submit']", deleteAnswer);

function deleteAnswer(e) {
    e.preventDefault();

    var deleteBtn = $(this);
    var url = $(".delete-comment-form").attr("action");

    $.ajax({
        type : 'delete',
        url : url,
        dataType : 'json',
        error : function (xhr, status) {
            console.log("error");
        },
        success : function (data, status) {
            console.log("success" + data);
            if (data.valid) {
                deleteBtn.closest("article").remove();
            } else {
                alert(data.errorMessage);
            }

            var count = parseInt($(".qna-comment-count strong").html());
            $(".qna-comment-count strong").text(count - 1);
        }
    });
}

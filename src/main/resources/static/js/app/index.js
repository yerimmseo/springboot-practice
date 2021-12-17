var main = {
    init : function() {
        var _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
    },
    save : function() {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();

// var main = {...}
// 여러 사람이 참여하는 프로젝트에서는 중복된 함수이름은 자주 발생할 수 있으므로 유효범위를 만들어 사용
// index 객체 안에서만 function이 유효하기 때문에 다른 JS와 겹칠 위험이 사라짐
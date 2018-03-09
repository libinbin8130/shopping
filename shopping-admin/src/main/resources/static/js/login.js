$(function(){
    var u = $('#form input[name=userName]');
    var p = $('#form input[name=password]');
    var v = $('#form input[name=validateCode]');

    $("#validate-code a").bind('click',refreshValidateCode);
    $("#validate-code img").bind('click',refreshValidateCode);

    function refreshValidateCode(){
        $("#validate-code img").attr('src','/captcha?r=' +Math.random());
    }

    $("#login-btn").click(function(){
        if(u.val() === ""){
            u.focus();
            return false;
        }else if(p.val() === ""){
            p.focus();
            return false;
        }else if(v.val() === ""){
            v.focus();
            return false;
        }
        $('#login-btn').addClass('disabled').attr('disabled', true);
        $('#login-btn').val('登录中...');

        $.ajax({
            url:$('#form').attr('action'),
            type:'post',
            contentType:"application/json",
            dataType:'json',
            data:util.form.formDataToJsonstring("#form"),
            error:function(){
                $('#login-btn').removeClass('disabled').attr('disabled', true);
                $('#login-btn').val('登 录');
                refreshValidateCode();
                $('#login-errors').text("系统繁忙，请稍后再试！");
            },
            success:function(data){
                if(data.code === 200){
                    window.location.href = '/index';
                    return;
                }
                $('#login-btn').removeClass('disabled').attr('disabled', false);
                $('#login-btn').val('登 录');
                refreshValidateCode();
                v.val('');
                $('#login-errors').text(data.errors);
            }
        });
    });
})


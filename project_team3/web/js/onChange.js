

$(document).ready(function(){
    $('input[id^="materialInput"').on('change foucusout',function(){
        var target = this;
        var $target = $(target);
        
        $target.attr("value",$target.val());
    });
});
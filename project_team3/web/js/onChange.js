

$(document).ready(function(){
    $('input[name^="product"').on('change foucusout',function(){
        var target = this;
        var $target = $(target);
        
        $target.attr("value",$target.val());
    });
});
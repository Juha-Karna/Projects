jQuery(document).ready(function($) {
    var form = $('form[name="form1"]'),
        radio = $('input[name="member_level"]'),
        choice = '';

    radio.change(function(e) {
        choice = this.value;

        if (choice === 'newbie') {
            form.attr('action', '/adduser');
        } 
        else if (choice === 'average') {
            form.attr('action', '/addcandinate');
        }
        else if (choice === 'master') {
            form.attr('action', '/addadmin');
        }
    });
});
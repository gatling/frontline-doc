import Clipboard from 'clipboard';

var $codes = document.querySelectorAll('.highlight');

function copyButton() {
    var copy = document.createElement("button");
    copy.className = "btn-clipboard btn btn-sm btn-link";
    return copy;
}

function statusButton(parent) {
    var status = document.createElement("span");
    status.className = "copy-status";
    parent.append(status);
}

function clipboardButton(element) {
    var copy = copyButton();
    statusButton(copy);
    element.prepend(copy);
}

for (var i = 0, len = $codes.length; i < len; i++) {
    clipboardButton($codes[i]);
}

var clipboard = new Clipboard('.btn-clipboard', {
    target: function(trigger) {
        return trigger.nextElementSibling;
    }
});

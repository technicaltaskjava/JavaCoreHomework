/**
 * Created by Slithy on 15.05.2016.
 */
function getNewMessage() {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'prediction', false);
    xhr.send();
    document.getElementById('prediction-text').innerText = xhr.responseText;
}

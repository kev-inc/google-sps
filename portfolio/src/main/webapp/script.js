setInterval(loopGreeting,2000)

function loopGreeting() {
    var slides = document.getElementsByClassName("banner-left")
    var current = 0

    for(var i = 0; i < slides.length; i++) {
        slides[i].style.opacity = 0
    }
    current = (current != slides.length - 1) ? current + 1 : 0
    slides[current].style.opacity = 1
}

function getAboutMeContent() {
    fetch('/data').then(response => response.text()).then((content) => {
        document.getElementById('aboutme_content').innerText = content
    })
}
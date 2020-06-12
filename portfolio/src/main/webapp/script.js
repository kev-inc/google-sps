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

function getComments() {
    fetch('/data').then(response => response.json()).then((comments) => {
        const commentsDiv = document.getElementById("comments")
        comments.forEach(x => {
            console.log(x)
            commentsDiv.appendChild(
                createComment(x)
            )
        })
    })
}

function createComment(comment) {
    const p = document.createElement("p")
    const el = document.createElement("i")
    el.innerText = "\"" + comment + "\""
    p.appendChild(el)
    return p
} 


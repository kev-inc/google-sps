var slides = document.getElementsByClassName("banner-left")
var current = 0

setInterval(loopGreeting,2000)

function loopGreeting() { 
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
            commentsDiv.appendChild(
                createComment(x)
            )
        })
    })
}

function createComment(comment) {
    const p = document.createElement("p")
    const cmt = document.createElement("i")
    cmt.innerText = "\"" + comment.message + "\""
    p.appendChild(cmt)

    const emoji = document.createElement("span")
    emoji.innerText = " - "
    if(comment.score > 0.5) {
        emoji.innerText += "😁😁😁"
    } else if(comment.score > 0) {
        emoji.innerText += "🙂🙂🙂"
    } else if(comment.score > -0.5) {
        emoji.innerText += "😕😕😕"
    } else {
        emoji.innerText += "😒😒😒"
    }
    p.appendChild(emoji)
    return p
} 


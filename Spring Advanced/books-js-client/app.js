let loadBookBtn = document.getElementById("loadBooks");

loadBookBtn.addEventListener('click', reloadBooks)

//TODO Create new book

function reloadBooks() {
    // window.alert('Reload books clicked')

    let booksContainer = document.getElementById('books-container')
    booksContainer.innerHTML = ''

    fetch("http://localhost:8080/api/books")
        .then(response => response.json())
        .then(json => json.forEach(book => {
            //  console.log('Book: ' + JSON.stringify(book))

            let row = document.createElement('tr')

            let titleCol = document.createElement('td')
            let authorCol = document.createElement('td')
            let isbnCol = document.createElement('td')
            let actionCol = document.createElement('td')

            titleCol.textContent = book.title
            authorCol.textContent = book.author.name
            isbnCol.textContent = book.isbn
            let deleteBtn = document.createElement('button')
            deleteBtn.innerText = 'DELETE'
            deleteBtn.dataset.id = book.id
            deleteBtn.addEventListener('click', deleteBtnClicked)
            actionCol.appendChild(deleteBtn)

            row.appendChild(titleCol)
            row.appendChild(authorCol)
            row.appendChild(isbnCol)
            row.appendChild(actionCol)

            booksContainer.appendChild(row)
        }))
}

function deleteBtnClicked(event) {
    let bookId = event.target.dataset.id;

    deleteBook(bookId)
}

function deleteBook(bookId) {
    var requestOptions = {
        method: 'DELETE'
    }

    fetch(`http://localhost:8080/api/books/${bookId}`, requestOptions)
        .then(_ => reloadBooks())
        .catch(error => console.log('error', error))
}
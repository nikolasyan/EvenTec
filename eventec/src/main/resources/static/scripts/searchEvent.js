const inputSearch = document.querySelector('.search input')
const cardItemContainer = document.querySelector('main') 

const filterCards = (cards, inputValue, returnMatchedCards) => cards
    .filter(cards => {
        const matchedCards = cards.textContent.toLowerCase().includes(inputValue)
        return returnMatchedCards ? matchedCards : !matchedCards

    })


const hideCards = (cards, inputValue) =>{
    filterCards(cards, inputValue, false)
    .forEach(card => {
        card.classList.add('hidden')
    })
}

const showCards = (cards, inputValue) =>{
    filterCards(cards, inputValue, true)
    .forEach(card => {
        card.classList.remove('hidden')
    })

}

inputSearch.addEventListener('input', event => {
    const inputValue = event.target.value.trim().toLowerCase()
    const cards = Array.from(cardItemContainer.children)
   
    hideCards(cards, inputValue)
    showCards(cards, inputValue)


})

// https://www.youtube.com/watch?v=OosED-pYNkQ
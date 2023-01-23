import { onSnake, expandSnake } from './mato.js'
import { randomGridPosition } from './grid.js'
export var score = 0

let food = getRandomFoodPosition()
const EXPANSION_RATE = 1
//Madon Kasvu Cheat Code^

//Kun mato syö ruoan se kasvaa EXPANSION_RATE määrän ja sitten ruoka saa random sijainnin
export function update() {
    if (onSnake(food)) {
        expandSnake(EXPANSION_RATE)
        food = getRandomFoodPosition()

        // score
        score += 1;
        document.getElementById('score').innerHTML = score;
    }
}
//piirretään ruoka gamebaordille
export function draw(gameBoard) {
    const foodElement = document.createElement('div')
    foodElement.style.gridRowStart = food.y
    foodElement.style.gridColumnStart = food.x
    foodElement.classList.add('food')
    gameBoard.appendChild(foodElement)
}
//Ruoan random sijainti
function getRandomFoodPosition() {
    let newFoodPosition
    while (newFoodPosition == null || onSnake(newFoodPosition)) {
        newFoodPosition = randomGridPosition()
    }
    return newFoodPosition
}
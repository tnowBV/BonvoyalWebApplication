let currentSlide = 0;
const slides = document.querySelectorAll('.slide-content');

function nextSlide() {
    if (currentSlide < slides.length - 1) {
        slides[currentSlide].classList.remove('active');
        currentSlide++;
        slides[currentSlide].classList.add('active');
    }
}

function submitForm() {
    const destination = document.getElementById('destination').value;
    const days = document.getElementById('days').value;
    const interests = document.getElementById('interests').value;

    alert(`Your itinerary to ${destination} for ${days} days with interests in ${interests} is being generated!`);
    // Future enhancement: redirect to a generated itinerary page
}

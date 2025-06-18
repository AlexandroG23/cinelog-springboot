// Modal Peliculas

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".open-modal-btn").forEach(button => {
        button.addEventListener("click", () => {
            const title = button.getAttribute("data-title");
            const overview = button.getAttribute("data-overview");
            const backdrop = button.getAttribute("data-backdrop");
            const release = button.getAttribute("data-release");
            const trailer = button.getAttribute("data-trailer");

            openModal(title, overview, backdrop, trailer, release);
        });
    });
});

function openModal(title, overview, backdropUrl, trailerKey, releaseDate) {
    document.getElementById('modalTitle').textContent = title;
    document.getElementById('modalOverview').textContent = overview || "Sin descripción disponible.";
    document.getElementById('modalBackdrop').src = backdropUrl || '';

    // Fecha de estreno
    const dateObj = new Date(releaseDate);
    let releaseText = "📅 Fecha de estreno no disponible";
    if (!isNaN(dateObj)) {
        releaseText = `📅 Estreno: ${dateObj.toLocaleDateString('es-ES', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        })}`;
    }
    document.getElementById('modalReleaseDate').textContent = releaseText;

    // Trailer
    const trailerContainer = document.getElementById('trailerContainer');
    const trailerIframe = document.getElementById('modalTrailer');
    if (trailerKey) {
        trailerIframe.src = `https://www.youtube.com/embed/${trailerKey}`;
        trailerContainer.classList.remove('hidden');
    } else {
        trailerIframe.src = '';
        trailerContainer.classList.add('hidden');
    }

    document.getElementById('movieModal').classList.remove('hidden');
}

function closeModal() {
    document.getElementById('movieModal').classList.add('hidden');
    document.getElementById('modalTrailer').src = '';
    document.getElementById('modalBackdrop').src = '';
}

// Modal Eliminar

function abrirModalEliminar(peliculaId) {
    const modal = document.getElementById('modalEliminar');
    const form = document.getElementById('formEliminar');

    // Cambiar la acción del formulario dinámicamente
    form.action = `/favoritos/eliminar/${peliculaId}`;

    // Mostrar modal
    modal.classList.remove('hidden');
    modal.classList.add('flex');
}

function cerrarModalEliminar() {
    const modal = document.getElementById('modalEliminar');

    modal.classList.add('hidden');
    modal.classList.remove('flex');
}

// GSAP

gsap.registerPlugin(SplitText)

const splitText = new SplitText(".text", {
    type: "words, chars, lines",
})

gsap.from(splitText.words, {
    duration: 1,
    y: 15,
    stagger: .1, // Segundos entre animación de cada elemento
    autoAlpha: 0,
    filter: "blur(5px)",
})


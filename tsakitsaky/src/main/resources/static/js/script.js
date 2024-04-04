// sidebar
document.querySelectorAll('.sidebar-dropdown-toggle').forEach(function(item) {
    item.addEventListener('click', function (e) {
        e.preventDefault()
        const parent = item.closest('.group')
        if(parent.classList.contains('selected')) {
            parent.classList.remove('selected')
        } else {
            document.querySelectorAll('.sidebar-dropdown-toggle').forEach(function(i) {
                i.closest('.group').classList.remove('selected')
            })
            parent.classList.add('selected')
        }
    })
})



// Récupère le paramètre de l'URL après la redirection
const urlParams = new URLSearchParams(window.location.search);
const authError = urlParams.get('error');
const authSuccess = urlParams.get('success');

// Si le paramètre 'error' est présent et a la valeur 'true', affiche le toast
if (authError === 'true' || authSuccess === 'true') {
    showToast();
}

function showToast() {
    var toast = document.getElementById("toast");
    toast.classList.remove("hidden");
    setTimeout(function() {
        toast.classList.add("hidden");
    }, 3000);
}
import { preencherGrafico } from "./gauge.js";
import { preencherJustGageCharts } from "./justgagecharts.js";

// Função para unificar dados dos endpoints individuais
async function carregarDadosSensores() {
  try {
    const [resEstacao, resPh, resPluv] = await Promise.all([
      fetch("https://spring-moringa.onrender.com/api/DadosEstacao").then((res) => res.json()),
      fetch("https://spring-moringa.onrender.com/api/SensorDePh").then((res) => res.json()),
      fetch("https://spring-moringa.onrender.com/api/Pluviometro").then((res) => res.json())
    ]);

    const ultimoEstacao = resEstacao.at(-1);
    const ultimoPh = resPh.at(-1);
    const ultimoPluv = resPluv.at(-1);

    // Unifica os dados em um objeto único para os gráficos
    const dadosUnificados = {
      ...ultimoEstacao,
      ph: ultimoPh?.ph,
      pluviometria: ultimoPluv?.medidaDeChuvaCalculado
    };

    return dadosUnificados;
  } catch (error) {
    console.error("Erro ao buscar dados dos sensores:", error);
    return null;
  }
}

document.addEventListener("DOMContentLoaded", () => {
  const map = L.map("map").setView([-8.08729, -39.57953], 12);

  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 18,
    attribution: '&copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
  }).addTo(map);

  L.Control.geocoder({ defaultMarkGeocode: true }).addTo(map);

  const painel = document.getElementById("painelGraficos");
  const puxador = document.getElementById("puxador");
  const historicoLink = document.getElementById("linkHistorico");
  const fecharBtn = document.getElementById("fecharPainel");

  fecharBtn.addEventListener("click", () => painel.classList.remove("aberto"));
  puxador.addEventListener("click", () => painel.classList.remove("aberto"));

  carregarDadosSensores().then((dados) => {
    if (!dados) {
      console.error("Nenhum dado unificado foi retornado.");
      return;
    }

    const marker = L.marker([-8.08729, -39.57953])
      .addTo(map)
      .bindPopup("Parnamirim")
      .on("click", () => {
        console.log("Clique no marcador: dado unificado:", dados);

        preencherJustGageCharts(dados);
        preencherGrafico(dados);

        painel.classList.add("aberto");
        puxador.style.display = "flex";
        historicoLink.style.display = "block";
        historicoLink.href = `historico.html`; // ou com parâmetro se quiser
      });
  });
});

function getAirport(latitute_deg, longitude_deg) {
  var mymap = L.map("airport_map").setView(
    [latitute_deg, longitude_deg],
    13,
    5
  );
  L.tileLayer("https://{s}.google.com/vt/lyrs=s&x={x}&y={y}&z={z}", {
    maxZoom: 20,
    subdomains: ["mt0", "mt1", "mt2", "mt3"],
  }).addTo(mymap);
}

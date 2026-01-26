package com.example.grandesmandos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;

import java.util.Objects;


public class Maps extends Fragment implements OnMapReadyCallback {
    private View rootView;
    private GoogleMap mMap;
    private MapView mapView;

    private Location location;
    private LocationManager locationManager;

    private Geocoder geocoder;
    private final int ACCESS_LOCATION_REQUEST_CODE = 10001;
    FusedLocationProviderClient fusedLocationProviderClient;

    LocationRequest locationRequest;

    public Maps() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_maps, container, false);
        Places.initialize(requireContext(),"@string/API_KEY");



        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView =(MapView) rootView.findViewById(R.id.map);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

            geocoder = new Geocoder(getContext());
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

            locationRequest = LocationRequest.create();
            locationRequest.setInterval(500);
            locationRequest.setFastestInterval(500);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }


    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(13);
        mMap.setMaxZoomPreference(18);


        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation();
            zoomToUserLocation();

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission
                    .ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        ACCESS_LOCATION_REQUEST_CODE);

            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        ACCESS_LOCATION_REQUEST_CODE);

            }
        }

        try {
            MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_json);
            mMap.setMapStyle(style);

        } catch (Exception exception) {
            Log.e("onMapReady", exception.getMessage());
        }


        // Fiscalía Regional de Toluca
        LatLng Toluca = new LatLng(19.28918721, -99.63832414);
        mMap.addMarker(new MarkerOptions().position(Toluca)
                .title("Fiscalía Regional de Toluca")
                .snippet("Tel: 722 2 26 16 00")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Tejupilco
        LatLng Tejupilco = new LatLng(18.89736226, -100.148468);
        mMap.addMarker(new MarkerOptions().position(Tejupilco)
                .title("Fiscalía Regional de Tejupilco")
                .snippet("Tel: 724 26 70 914")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Atlacomulco
        LatLng Atlacomulco = new LatLng(19.78832779, -99.87191051);
        mMap.addMarker(new MarkerOptions().position(Atlacomulco)
                .title("Fiscalía Regional de Atlacomulco")
                .snippet("Tel: 712 1 24 84 88")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Valle de Bravo
        LatLng ValleDeV = new LatLng(19.20418155, -100.1339023);
        mMap.addMarker(new MarkerOptions().position(ValleDeV)
                .title("Fiscalía Regional de Valle de Bravo")
                .snippet("Tel: 726 26 26 143")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Ixtapan de la Sal
        LatLng Ixtapan = new LatLng(18.90661043, -99.64203065);
        mMap.addMarker(new MarkerOptions().position(Ixtapan)
                .title("Fiscalía Regional de Ixtapan de la Sal")
                .snippet("Tel: 714 14 09282")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Regional de Tlalnepantla
        LatLng Tlalnepantla = new LatLng(19.58553702, -99.19630542);
        mMap.addMarker(new MarkerOptions().position(Tlalnepantla)
                .title("Fiscalía Regional de Tlalnepantla")
                .snippet("Tel: 55 53 21 34 00, Ext. 3704")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Naucalpan
        LatLng Naucalpan = new LatLng(19.45308804, -99.22105171);
        mMap.addMarker(new MarkerOptions().position(Naucalpan)
                .title("Fiscalía Regional de Naucalpan")
                .snippet("Tel: 55 72 61 63 97")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Regional de Ecatepec
        LatLng Ecatepec = new LatLng(19.5976768, -99.04179822);
        mMap.addMarker(new MarkerOptions().position(Ecatepec)
                .title("Fiscalía Regional de Ecatepec")
                .snippet("Tel: 55 57 87 36 41")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Regional de Tecamac
        LatLng Tecamac = new LatLng(19.71670347, -98.97288119);
        mMap.addMarker(new MarkerOptions().position(Tecamac)
                .title("Fiscalía Regional de Tecamac")
                .snippet("Tel: 55 59 34 30 70")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Cuautitlan
        LatLng Cuautitlan = new LatLng(19.65775711, -99.20914864);
        mMap.addMarker(new MarkerOptions().position(Cuautitlan)
                .title("Fiscalía Regional de Cuautitlan")
                .snippet("Tel: 55 58 68 61 59")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Texcoco
        LatLng Texcoco = new LatLng(19.50931145, -98.87169906);
        mMap.addMarker(new MarkerOptions().position(Texcoco)
                .title("Fiscalía Regional de Texcoco")
                .snippet("Tel: 59 59 54 47 54")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Amecameca
        LatLng Amecameca = new LatLng(19.14201726, -98.77341932);
        mMap.addMarker(new MarkerOptions().position(Amecameca)
                .title("Fiscalía Regional de Amecameca")
                .snippet("Tel: 59 79 78 93 91")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía Regional de Nezahualcoyotl
        LatLng Nezahualcoyotl = new LatLng(19.40882699, -99.01739295);
        mMap.addMarker(new MarkerOptions().position(Nezahualcoyotl)
                .title("Fiscalía Regional de Nezahuacóyotl")
                .snippet("Tel: 55 51 12 72 00")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Asuntos Especiales
        LatLng AsuntosEspeciales = new LatLng(19.28996037, -99.6379326);
        mMap.addMarker(new MarkerOptions().position(AsuntosEspeciales)
                .title("Fiscalía de Asuntos Especiales")
                .snippet("Tel: 722 215 99 51 ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Fiscalía de Combate a la Corrupción
        LatLng Corrupcion = new LatLng(19.28988236, -99.63924768);
        mMap.addMarker(new MarkerOptions().position(Corrupcion)
                .title("Fiscalía Especializada en Combate a la Corrupción")
                .snippet("Tel: 722 213 05 05, Ext.3336 ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Investigación de Tortura
        LatLng Tortura = new LatLng(19.25563501, -99.66003015);
        mMap.addMarker(new MarkerOptions().position(Tortura)
                .title("Fiscalía Especializada para el Delito de Tortura")
                .snippet("Tel: 722 2 12 73 82")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Homicidio Tlalnepantla
        LatLng HomicidioTlal = new LatLng(19.28251799, -99.51688938);
        mMap.addMarker(new MarkerOptions().position(HomicidioTlal)
                .title("Fiscalía Especializada de Homicidios de Toluca")
                .snippet("Tel: 728 28 206 21")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Homicidio-Valle de México
        LatLng HomicidioVallMex = new LatLng(19.59757671, -99.04173432);
        mMap.addMarker(new MarkerOptions().position(HomicidioVallMex)
                .title("Fiscalía Especializada de Homicidio del Valle de México")
                .snippet("Tel: 55 51 16 24 34")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Secuestro Valle de México
        LatLng SecuestroValleMex = new LatLng(19.52630006, -99.11013738);
        mMap.addMarker(new MarkerOptions().position(SecuestroValleMex)
                .title("Fiscalía Especializada de Secuestro del Valle de México")
                .snippet("Tel: 55 82 83 20 13")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Secuestro Zona Oriente
        LatLng SecuestroOriente = new LatLng(19.40883626, -99.01739134);
        mMap.addMarker(new MarkerOptions().position(SecuestroOriente)
                .title("Fiscalía Especializada de Secuestro Zona Oriente")
                .snippet("Tel: 55 51 12 70 32")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Secuestro Valle de Toluca
        LatLng SecuestroToluca = new LatLng(19.31396252, -99.54075276);
        mMap.addMarker(new MarkerOptions().position(SecuestroToluca)
                .title("Fiscalía Especializada de Secuestro del Valle de Toluca")
                .snippet("Tel: 72 22 36 29 08, Ext.53113")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Delitos contra el Transporte
        LatLng Transporte = new LatLng(19.58538457, -99.1962619);
        mMap.addMarker(new MarkerOptions().position(Transporte)
                .title("Fiscalía Especializada Contra el Transporte")
                .snippet("Tel: 55 53 17 81 75")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Delitos RV Valle de México
        LatLng RVValleMex = new LatLng(19.58672247, -99.02593011);
        mMap.addMarker(new MarkerOptions().position(RVValleMex)
                .title("Fiscalía Especializada de Robo de Vehículo-Valle de Mex.")
                .snippet("Tel: 55 58 38 14 71")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Delitos RV Toluca-Tlalnepantla
        LatLng RVToluca = new LatLng(19.2888797, -99.63821568);
        mMap.addMarker(new MarkerOptions().position(RVToluca)
                .title("Fiscalía Especializada de Robo de Vehículo-Toluca")
                .snippet("Tel: 722 2 26 16 00, Ext. 3555")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Delitos RV Oriente
        LatLng RVOriente = new LatLng(19.42353481, -99.02380675);
        mMap.addMarker(new MarkerOptions().position(RVOriente)
                .title("Fiscalía Especializada de Robo de Vehículo-Oriente")
                .snippet("Tel: 55 22 28 03 61")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía de Robo con Vioolencia y Cuantía Mayor
        LatLng RobconViol = new LatLng(19.25530884, -99.66018143);
        mMap.addMarker(new MarkerOptions().position(RobconViol)
                .title("Fiscalía de Delitos de Robo con Violencia y Cuantía Mayor")
                .snippet("Tel: 722 2 12 74 54")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Contra el Ambiente
        LatLng Ambiente = new LatLng(19.54049879, -99.21142182);
        mMap.addMarker(new MarkerOptions().position(Ambiente)
                .title("Fiscalía Especializada de Delitos Contra el Ambiente")
                .snippet("Tel: 55 55 65 68 09")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Trata de Personas
        LatLng Trata = new LatLng(19.29895967, -99.66849332);
        mMap.addMarker(new MarkerOptions().position(Trata)
                .title("Fiscalía Especializada de Trata de Personas")
                .snippet("Tel: 722 1 67 32 16 ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Feminicidio
        LatLng Feminicidio = new LatLng(19.2985445, -99.6686623);
        mMap.addMarker(new MarkerOptions().position(Feminicidio)
                .title("Fiscalía Especializada de Feminicidios")
                .snippet("Tel: 55 53 17 34 20")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Delitos Electorales
        LatLng Electorales = new LatLng(19.29052948, -99.66770896);
        mMap.addMarker(new MarkerOptions().position(Electorales)
                .title("Fiscalía Especializada de Delitos Electorales")
                .snippet("Tel: 722 1 67 32 40")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Desaparición Forzada
        LatLng DesForz = new LatLng(19.29897638, -99.66849454);
        mMap.addMarker(new MarkerOptions().position(DesForz)
                .title("Fiscalía Especializada en Desaparición Forzada")
                .snippet("Tel: 722 2 83 20 12")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Fiscalía Adolescentes
        LatLng Adolescentes = new LatLng(19.29217224, -99.73347275);
        mMap.addMarker(new MarkerOptions().position(Adolescentes)
                .title("Fiscalía Especializada en Adolescentes")
                .snippet("Tel: 722 2 18 74 54")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Unidad de Análisis Táctico Operativo
        LatLng UATO = new LatLng(19.28919051, -99.63852331);
        mMap.addMarker(new MarkerOptions().position(UATO)
                .title("Unidad de Análisis Táctico Operativo (UATO)")
                .snippet("Tel: 22 2 26 16 00, Ext. 3278")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));


        //Agencia del Ministerio Público de Zinacantepec
        LatLng MPZinacantepec = new LatLng(19.29203682, -99.73301732);
        mMap.addMarker(new MarkerOptions().position(MPZinacantepec)
                .title("Agencia del Ministerio Público de Zinacantepec")
                .snippet("Tel: 7222180613")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Agencia del Ministerio Público Ocra-Toluca
        LatLng OCRAToluca = new LatLng(19.28938985, -80.43366903);
        mMap.addMarker(new MarkerOptions().position(OCRAToluca)
                .title("Agencia del Ministerio Público de OCRA-Toluca")
                .snippet("Tel: 7222786574")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Agencia del Ministerio Público Violencia Familiar y Genero
        LatLng MPViolFam = new LatLng(19.25326149, -99.63512352);
        mMap.addMarker(new MarkerOptions().position(MPViolFam)
                .title("Ministerio Público de Violencia Familiar y Género")
                .snippet("Tel: 7222179392")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.genero_fisc)));

        //Agencia del Ministerio Público de Metepec
        LatLng MPMetepec = new LatLng(19.26684862, -99.57580174);
        mMap.addMarker(new MarkerOptions().position(MPMetepec)
                .title("Agencia del Ministerio Público de Metepec")
                .snippet("Bella Vista, 52172 Metepec, Méx.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Agencia del Ministerio Público de Xonacatlan
        LatLng MPXonacatlan = new LatLng(19.39490179, -99.53496355);
        mMap.addMarker(new MarkerOptions().position(MPXonacatlan)
                .title("Agencia del Ministerio Público de Xonacatlán")
                .snippet("52016 Méx. ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Agencia del Ministerio Público de Lerma
        LatLng MPLerma = new LatLng(19.2826546, -99.51699362);
        mMap.addMarker(new MarkerOptions().position(MPLerma)
                .title("Agencia del Ministerio Público de Lerma")
                .snippet("Tel: 7282852099")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Agencia del Ministerio Público Xalostoc
        LatLng MPXalostoc = new LatLng(19.51589909, -99.08806463);
        mMap.addMarker(new MarkerOptions().position(MPXalostoc)
                .title("Agencia del Ministerio Público Xalostoc")
                .snippet("Tel: 5557142698")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Agencia del Ministerio Público Federal Ecatepec
        LatLng MPFEcatepec = new LatLng(19.522299, -99.03385716);
        mMap.addMarker(new MarkerOptions().position(MPFEcatepec)
                .title("Agencia del Ministerio Público Federal Ecatepec")
                .snippet("Ubicado en: Center Plazas")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Agencia del Ministerio Público Federal Toluca
        LatLng MPFToluca = new LatLng(19.31823586, -99.63684309);
        mMap.addMarker(new MarkerOptions().position(MPFToluca)
                .title("Agencia del Ministerio Público Federal Toluca")
                .snippet("Tel: ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Ministerio Público Ixtapaluca
        LatLng MPIxtapaluca = new LatLng(19.31748507, -98.93542545);
        mMap.addMarker(new MarkerOptions().position(MPIxtapaluca)
                .title("Centro de Justicia de Ixtapaluca")
                .snippet("Tel: 525513141364")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Ministerio Público Chalco
        LatLng MPChalco = new LatLng(19.2641315412028, -98.8913498644261);
        mMap.addMarker(new MarkerOptions().position(MPChalco)
                .title("Centro de Justicia de Chalco")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Centro de Justicia Género Amecameca
        LatLng generoChalco = new LatLng(19.1423033, -98.77328523);
        mMap.addMarker(new MarkerOptions().position(generoChalco)
                .title("Centro de Justicia para Mujeres Amecameca ")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.genero_fisc)));

        //Centro de Justicia para Mujeres Cuautitlán Izcalli:
        LatLng generoCuatitlanI = new LatLng(19.65446464, -99.20858513);
        mMap.addMarker(new MarkerOptions().position(generoCuatitlanI)
                .title("Centro de Justicia para Mujeres de Cuatitlan Izcalli")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.genero_fisc)));

        //Centro de Justicia para Mujeres Ecatepec:
        LatLng generoEcatepec = new LatLng(19.59549025, -99.05171675);
        mMap.addMarker(new MarkerOptions().position(generoEcatepec)
                .title("Centro de Justicia para Mujeres de Ecatepec")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.genero_fisc)));

        //Centro de Justicia para Mujeres Toluca
        LatLng generoToluca = new LatLng(19.29865841, -99.66865157);
        mMap.addMarker(new MarkerOptions().position(generoToluca)
                .title("Centro de Justicia para Mujeres de Toluca")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.genero_fisc)));

        //Ministerio Público Cuautitlan
        LatLng MPCuauti = new LatLng(19.65452225, -99.20870573);
        mMap.addMarker(new MarkerOptions().position(MPCuauti)
                .title("Agencia del Ministerio Público de Cuautitlan Izcalli")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Ministerio Público Tulti
        LatLng MPTulti = new LatLng(19.65310945, -99.17105632);
        mMap.addMarker(new MarkerOptions().position(MPTulti)
                .title("Agencia del Ministerio Público de Tultitlan")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Ministerio Público Nicolas Romero
        LatLng MPNicoRM = new LatLng(19.62583158, -99.31250936);
        mMap.addMarker(new MarkerOptions().position(MPNicoRM)
                .title("Agencia del Ministerio Público de Nicolas Romero")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Ministerio Público Huixquilucan
        LatLng MPHuixqui = new LatLng(19.35908994, -99.34504676);
        mMap.addMarker(new MarkerOptions().position(MPHuixqui)
                .title("Agencia del Ministerio Público de Nicolas Romero")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Ministerio Público Valle de Bravo
        LatLng MPValle = new LatLng(19.20453274, -100.1336179);
        mMap.addMarker(new MarkerOptions().position(MPValle)
                .title("Agencia del Ministerio Público de Valle de Bravo")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

        //Ministerio Público Chimalhuacan
        LatLng MPChimal = new LatLng(19.41294254, -98.93595183);
        mMap.addMarker(new MarkerOptions().position(MPChimal)
                .title("Agencia del Ministerio Público de Chimalhuacan")
                .snippet("Tel:")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.fiscalia)));

    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
        }
    };

    private void starLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.requestLocationUpdates
                (locationRequest, locationCallback, Looper.getMainLooper());

    }

    private void stopLocationUpdates(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);

    }

    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    private void zoomToUserLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                mMap.addMarker(new MarkerOptions().position(latLng).title("Aquí esta tu ubicación")
                        .snippet("latitud: " +latLng.latitude + " / " + " longitud: " + latLng.longitude)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.patrulla1)));





            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == ACCESS_LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
                zoomToUserLocation();

            }else{

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



    }
}
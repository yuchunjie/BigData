
spm('Defaults', 'PET');
global defaults;

normalise_flags = defaults.normalise;
normalise_flags.write.vox = [3.9 3.9 3.9];

template = spm_vol(spm_select(Inf, 'image', 'Select Image'));

source = spm_vol(spm_select(Inf, 'image', 'Select Image'));

[file_path file_name] = spm_fileparts(char(source.fname));
output_params = {fullfile(file_path, [file_name, '_sn.mat'])};

transform_parameter = spm_normalise(template.fname, source.fname, output_params{1}, ...
	normalise_flags.estimate.weight, '', normalise_flags.estimate);
	
spm_write_sn(source.fname, transform_parameter, normalise_flags.write);



%----
%# turn off gui in spm_figure.m at line 175, change Visible 'on' to 'off'
%global st;
%mat = st.vols{1}.premul*st.vols{1}.mat;
%M = spm_imatrix(mat);

%dimensions:
%D = st.vols{1}.dim(1:3)
%sprintf('%d x %d x %d', D);

%origin:
%O = mat\[0 0 0 1]'; 
%O=O(1:3)';
%sprintf('%.3g %.3g %.3g', O)

%voxel size:
%Z = M(7:9);
%sprintf('%.3g x %.3g x %.3g', Z);


%dir cos:
%R = spm_matrix([0 0 0 M(4:6)]);
%P = R(1:3,1:3);

%sprintf('%+5.3f %+5.3f %+5.3f', P(1,1:3));
%sprintf('%+5.3f %+5.3f %+5.3f', P(2,1:3));
%sprintf('%+5.3f %+5.3f %+5.3f', P(3,1:3));
